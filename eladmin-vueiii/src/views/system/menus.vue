<template>
    <div class="table-box">
        <div class="toolbar">
            <el-input class="input-width" v-model="query.title" placeholder="请输入 title 搜索"></el-input>
            <el-button type="primary" @click="queryData">搜索</el-button>
            <el-button type="primary" @click="resetQuery">重置</el-button>
            <el-button type="danger" @click="delteRows">批量删除</el-button>
            <el-button type="primary" @click="handleAdd">新增</el-button>
        </div>
        <el-table ref="multipleTableRef" :data="tableData.list" :row-class-name="tableRowClassName" row-key="id" @selection-change="handleSelectionChange">
            <el-table-column type="selection" reserve-selection width="55" />
            <el-table-column prop="sort" label="sort" align="center" />
            <el-table-column prop="id" label="id" align="center" />
            <el-table-column prop="name" label="name" align="center" show-overflow-tooltip />
            <el-table-column prop="path" label="path" align="center" show-overflow-tooltip />
            <el-table-column prop="component" label="component" align="center" show-overflow-tooltip />
            <el-table-column prop="title" label="title" align="center" />
            <el-table-column prop="icon" label="icon" align="center" v-slot="scope">
                <el-icon><User/></el-icon>
            </el-table-column>
            <el-table-column prop="hidden" label="hidden" align="center" />
            <el-table-column prop="keepAlive" label="keepAlive" align="center" />
            <el-table-column prop="permission" label="permission" align="center" show-overflow-tooltip />
            <el-table-column label="Operations" align="center" width="200">
                <template #default="scope">
                    <el-button text size="small" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button text size="small" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
            background
            :total="tableData.total"
            layout="sizes, total, prev, pager, next"
            :page-sizes="[10, 20, 50]"
            v-model:current-page="query.curPage"
            v-model:page-size="query.limit"
            @current-change="handleCurrentPage"
            @size-change="handleSizeChange">
        </el-pagination>
        <el-dialog v-model="dialogVisible" :title="dialogTitleComputed" :close-on-click-modal="false" :close-on-press-escape="false" :show-close="false" width="30%">
            <el-form :model="formInfo" :rules="formInfoRules" ref="ruleFormRef" label-width="120px">
                <el-form-item label="排序" prop="sort">
                    <el-input v-model="formInfo.sort" type="number" />
                </el-form-item>
                <el-form-item label="父节点id" prop="pid">
                    <el-input v-model="formInfo.pid" type="number" />
                </el-form-item>
                <el-form-item label="页面路径" prop="path">
                    <el-input v-model="formInfo.path" />
                </el-form-item>
                <el-form-item label="组件名称" prop="name">
                    <el-input v-model="formInfo.name" />
                </el-form-item>
                <el-form-item label="菜单编码" prop="permission">
                    <el-input v-model="formInfo.permission" />
                </el-form-item>
                <el-form-item label="所映射的组件" prop="component">
                    <el-input v-model="formInfo.component" />
                </el-form-item>
                <el-form-item label="菜单名称" prop="title">
                    <el-input v-model="formInfo.title" />
                </el-form-item>
                <el-form-item label="菜单图标" prop="icon">
                    <el-popover placement="top" :width="570" trigger="click" @show="popoverShow">
                        <template #reference>
                            <el-input v-model="formInfo.icon" placeholder="点击选择图标" readonly>
                                <template #prefix>
                                    <el-icon class="el-input__icon"><User/></el-icon>
                                </template>
                            </el-input>
                        </template>
                        <icon-select ref="iconSelectRef" @selected="iconSelected" />
                    </el-popover>
                </el-form-item>
                <!--
                <el-form-item label="类型" prop="type">
                    <el-radio-group v-model="formInfo.type">
                        <el-radio :label="0">目录</el-radio>
                        <el-radio :label="1">菜单</el-radio>
                        <el-radio :label="2">按钮</el-radio>
                    </el-radio-group>
                </el-form-item>
                -->
                <el-form-item label="是否显示菜单" prop="hidden">
                    <el-radio-group v-model="formInfo.hidden">
                        <el-radio :label="true" />
                        <el-radio :label="false" />
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="是否缓存页面" prop="keepAlive">
                    <el-radio-group v-model="formInfo.keepAlive">
                        <el-radio :label="true" />
                        <el-radio :label="false" />
                    </el-radio-group>
                </el-form-item>
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogCancel">取消</el-button>
                    <el-button type="primary" @click="dialogConfirm">确认</el-button>
                </span>
            </template>
        </el-dialog>
    </div>
</template>

<script setup>
    import useTable from "@/hooks/useTable";
    import IconSelect from "@/components/IconSelect.vue";
    import { onMounted, reactive, ref } from "vue";
    import { User } from "@element-plus/icons-vue";

    const {
        query,
        tableData,
        multipleTableRef,
        formInfo,
        ruleFormRef,
        dialogVisible,
        dialogTitleComputed,
        handleAdd,
        handleDelete,
        handleEdit,
        handleSelectionChange,
        handleCurrentPage,
        handleSizeChange,
        resetQuery,
        delteRows,
        dialogConfirm,
        dialogCancel,
        queryData
    } = useTable("sysMenuAdd", "sysMenuDeleteById/", "sysMenuUpdate", "sysMenuPage");
    formInfo.value = {
        sort: 0,
        pid: 0,
        path: "",
        name: "",
        component: "",
        title: "",
        icon: "dashboard",
        hidden: false,
        keepAlive: false,
        permission: ""
    };
    const formInfoRules = reactive({
        sort: [{ required: true, message: "Please input sort", trigger: "blur" }],
        pid: [{ required: true, message: "Please input pid", trigger: "blur" }],
        path: [{ required: true, message: "Please input path", trigger: "blur" }],
        name: [{ required: true, message: "Please input name", trigger: "blur" }],
        component: [{ required: true, message: "Please input component", trigger: "blur" }],
        title: [{ required: true, message: "Please input title", trigger: "blur" }],
        icon: [{ required: true, message: "Please input icon", trigger: "blur" }],
        hidden: [{ required: true, message: "Please input hidden", trigger: "blur" }],
        keepAlive: [{ required: true, message: "Please input keepAlive", trigger: "blur" }],
        permission: [{ required: true, message: "Please input permission", trigger: "blur" }],
    });
    const tableRowClassName = (row) => {
        if (row.row.pid === 0) {
            return "success-row";
        }
        return "";
    };
    const iconSelectRef = ref();
    const iconColor = ref("#000000");
    onMounted(() => {
        queryData();
    });
    function popoverShow() {
        iconSelectRef.value.reset();
    }
    function iconSelected(name) {
        formInfo.value.icon = name;
    }
    // function normalizer(node: any) {return {id: node.id, label: node.meta.title, children: node.children};}
</script>

<style scoped>
    @import "../../styles/mtable.css";
</style>
