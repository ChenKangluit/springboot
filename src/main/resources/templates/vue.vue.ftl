<template>
    <el-card class="main-card">
        <div class="title">{{ this.$route.name }}</div>
        <!-- 表格操作 -->
        <div class="operation-container" style="display: flex; margin: 10px">
            <el-button
                    type="primary"
                    size="small"
                    icon="el-icon-plus"
                    @click="openModel(null)"
            >
                新增
            </el-button>
            <el-button
                    type="danger"
                    size="small"
                    icon="el-icon-delete"
                    :disabled="this.${table.entityPath}IdList.length == 0"
                    @click="isDelete = true"
            >
                批量删除
            </el-button>
            <div style="margin-left: 20px">
                <el-input
                        v-model="keywords"
                        prefix-icon="el-icon-search"
                        size="small"
                        placeholder="请输入..."
                        style="width:200px"
                        @keyup.enter.native="search${entity}"
                />
                <el-button
                        type="primary"
                        size="small"
                        icon="el-icon-search"
                        style="margin-left:1rem"
                        @click="search${entity}"
                >
                    搜索
                </el-button>
            </div>
        </div>
        <!-- 表格展示 -->
        <el-table
                border
                style="margin: 10px"
                :header-cell-style="{ 'text-align': 'center' }"
                :cell-style="{ 'text-align': 'center' }"
                :data="${table.entityPath}List"
                @selection-change="selectionChange"
                v-loading="loading"
        >
            <!-- 表格列 -->
            <el-table-column type="selection" width="55" />
            <#list table.fields as item >
                <#if item.propertyName?contains("Time")>
                    <el-table-column  label="${item.comment}">
                        <template slot-scope="scope">
                            <i class="el-icon-time" style="margin-right:5px" />
                            {{ scope.row.${item.propertyName} | date }}
                        </template>
                    </el-table-column>
                <#elseif item.propertyName=="id">

                <#else>
                    <el-table-column prop="${item.propertyName}"
                                     label="${item.comment}"></el-table-column>
                </#if>
            </#list>
            <!-- 列操作 -->
            <el-table-column label="操作" width="200" >
                <template slot-scope="scope">
                    <el-link type="primary"
                             @click="openModel(scope.row)"
                             icon="el-icon-edit">编辑</el-link>
                    <el-popconfirm
                            title="确定删除吗？"
                            style="margin-left:1rem"
                            @confirm="deleteBy${entity}(scope.row.id)"
                    >
                            <el-link type="danger"
                                     slot="reference"
                                     icon="el-icon-delete">删除</el-link>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页 -->
        <el-pagination
                class="pagination-container"
                background
                @size-change="sizeChange"
                @current-change="currentChange"
                :current-page="pageNum"
                :page-size="pageSize"
                :total="total"
                :page-sizes="[10, 20]"
                layout="total, sizes, prev, pager, next, jumper"
        />
        <!-- 批量删除对话框 -->
        <el-dialog :visible.sync="isDelete" width="30%">
            <div class="dialog-title-container" slot="title">
                <i class="el-icon-warning" style="color:#ff9900" />提示
            </div>
            <div style="font-size:1rem">是否删除选中项？</div>
            <div slot="footer">
                <el-button @click="isDelete = false">取 消</el-button>
                <el-button type="primary" @click="deleteBy${entity}(null)">
                    确 定
                </el-button>
            </div>
        </el-dialog>
        <!-- 添加编辑对话框 -->
        <el-dialog :visible.sync="addOrEdit" width="30%">
            <div class="dialog-title-container" slot="title" ref="${table.entityPath}Title" />
            <el-form label-width="80px" size="medium" :model="${table.entityPath}Form">

                <#list table.fields as item>
                    <#if item.propertyName?contains("Time")>
                    <el-form-item  label="${item.comment}">
                        <el-date-picker
                                v-model="${table.entityPath}Form.${item.propertyName}"
                                type="date"
                                placeholder="请选择日期">
                        </el-date-picker>
                    </el-form-item>
                    <#elseif item.propertyName=="id">

                    <#else>
                            <el-form-item  label="${item.comment}">
                                <el-input v-model="${table.entityPath}Form.${item.propertyName}"/>
                            </el-form-item>
                    </#if>
                </#list>

            </el-form>

            <div slot="footer">
                <el-button @click="addOrEdit = false">取 消</el-button>
                <el-button type="primary" @click="addOrEdit${entity}">
                    确 定
                </el-button>
            </div>
        </el-dialog>
    </el-card>
</template>

<script>
  export default {

    data() {
      return {
        isDelete: false,
        loading: true,
        addOrEdit: false,
        keywords: null,
        ${table.entityPath}IdList: [],
        ${table.entityPath}List: [],
        ${table.entityPath}Form: {},
        pageNum: 1,
        pageSize: 10,
        total: 0
      };
    },
    created() {
      this.list${entity}();
    },
    methods: {
      selectionChange(${table.entityPath}List) {
        this.${table.entityPath}IdList = [];
        ${table.entityPath}List.forEach(item => {
          this.${table.entityPath}IdList.push(item.id);
        });
      },
      search${entity}() {
        this.pageNum = 1;
        this.list${entity}();
      },
      sizeChange(size) {
        this.pageSize = size;
        this.list${entity}();
      },
      currentChange(current) {
        this.pageNum = current;
        this.list${entity}();
      },

      deleteBy${entity}(id) {
        var param = {};
        if (id == null) {
          param = { ids: this.${table.entityPath}IdList };
        } else {
          param = { ids: [id] };
        }
        this.axios.delete("/api/${table.entityPath}", param.ids).then(res => {
          if (res.code === '200') {
            this.$notify.success({
              title: "成功",
              message: res.message
            });
            this.list${entity}();
          } else {
            this.$notify.error({
              title: "失败",
              message: res.message
            });
          }
          this.isDelete = false;
        });
      },

      list${entity}() {
        this.axios
            .get("/api/${table.entityPath}/page", {
              params: {
                pageNum: this.pageNum,
                pageSize: this.pageSize,
                keywords: this.keywords
              }
            })
            .then(res => {
              if(res.code === '200') {
                this.${table.entityPath}List = res.data.records;
                this.total = res.data.total;
                this.loading = false;
              }
            });
      },
      openModel(${table.entityPath}) {
        if (${table.entityPath} != null) {
          this.${table.entityPath}Form = JSON.parse(JSON.stringify(${table.entityPath}));
          this.$refs.${table.entityPath}Title.innerHTML = "修改";
        } else {
          this.${table.entityPath}Form = {};
          this.$refs.${table.entityPath}Title.innerHTML = "添加";
        }
        this.addOrEdit = true;
      },
      addOrEdit${entity}() {
        if (!this.${table.entityPath}Form) {
          this.$message.error("不能为空");
          return false;
        }

        this.axios
            .post("/api/${table.entityPath}", this.${table.entityPath}Form)
            .then(res => {
              if (res.code === '200') {
                this.$notify.success({
                  title: "成功",
                  message: res.message
                });
                this.list${entity}();
              } else {
                this.$notify.error({
                  title: "失败",
                  message: res.message
                });
              }
              this.addOrEdit = false;
            });
      }
    }
  };
</script>
