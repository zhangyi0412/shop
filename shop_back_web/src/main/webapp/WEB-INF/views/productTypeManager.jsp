<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>backend</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/userSetting.js"></script>
    <%--引入bootstrap分页控件--%>
    <script src="${pageContext.request.contextPath}/js/bootstrap-paginator.js"></script>
    <%--引入layer弹出层--%>
    <script src="${pageContext.request.contextPath}/layer/layer.js"></script>
    <script>
        $(function () {
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion: 3,
                currentPage:${pageInfo.pageNum},
                totalPages:${pageInfo.pages},
                pageUrl: function (type, page, current) {
                    return '${pageContext.request.contextPath}/back/productType/findAll?pageNum=' + page;//这里的page,就是点击的页码
                },
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "末页";
                        case "page":
                            return page;
                    }
                }
            });
        });
    </script>
    <script>
        //按名字查找名字
        function findProductTypeById(id) {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/back/productType/findProductTypeById",
                data: {
                    id: id
                },
                datatype: JSON,
                success: function (result) {
                    if (result.status == 1) {
                        $('#proTypeNum').val(result.data.id);
                        $('#proTypeName').val(result.data.name);
                    }
                }
            });
        }

        //添加
        function addProductType() {
            var productTypeName = $("#productTypeName").val();//获取输入框的值
            if (productTypeName == "") {
                layer.alert("输入为空", {icon: 2});
                return;
            }
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/back/productType/addProductType",
                data: {
                    name: productTypeName
                },
                success: function (msg) {
                    if(msg.status==1){
                        layer.alert(msg.message, {icon: 1},function () {
                            window.location.href="${pageContext.request.contextPath}/back/productType/findAll?pageNum=${pageInfo.pages}";
                        });
                    }else {
                        layer.alert(msg.message, {icon: 2});
                    }
                }

            });
        }
        //修改类型名字
        function modifyName(id,name) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/back/productType/modifyName",
                    data: {
                        id:id,
                        name:name
                    },
                    success: function (msg) {
                        if(msg.status==1){
                            layer.alert(msg.message, {icon: 1},function () {
                                window.location.href="${pageContext.request.contextPath}/back/productType/findAll?pageNum=${pageInfo.pageNum}";
                            });
                        }else {
                            layer.alert(msg.message, {icon: 2});
                        }
                    }
                });
        }
        //删除商品类型
        function deleteProdutType(id) {
            layer.confirm('确定删除？', {icon: 3, title:'提示'}, function(index){
                //后台异步删除
                $.ajax({
                    url:"${pageContext.request.contextPath}/back/productType/deleteProductType",
                    data:{
                        id:id
                    },
                    success:function (msg) {
                        if(msg.status==1){
                            layer.alert(msg.message, {icon: 1},function () {
                                window.location.href="${pageContext.request.contextPath}/back/productType/findAll?pageNum=${pageInfo.pages}"
                            });
                        }else {
                            layer.alert(msg.message, {icon: 2});
                        }
                    }
                });
                layer.close();
            });

        }
        //修改状态，btn为dom对象,由this对象传过来
        function modifyStatus(id,btn) {
            btnpre=$(btn).parent().prev();//将dom对象转化为jquery对象
            $.ajax({
                url:"${pageContext.request.contextPath}/back/productType/modifyStatus",
                data:{
                    id:id
                },
                success:function (msg) {
                    if(msg.status==1){
                       if(btnpre.text().trim()=="启用状态"){
                           btnpre.text("禁用状态");
                           $(btn).val("启用").removeClass("btn-danger").addClass("btn-success");

                       }else {
                           btnpre.text("启用状态");
                           $(btn).val("禁用").removeClass("btn-success").addClass("btn-danger");
                       }
                        layer.msg(msg.message);
                    }else {
                        layer.msg(msg.message);
                    }
                }
            });
        }
    </script>
</head>

<body>
<div class="panel panel-default" id="userSet">
    <div class="panel-heading">
        <h3 class="panel-title">商品类型管理</h3>
    </div>
    <div class="panel-body">
        <input type="button" value="添加商品类型" class="btn btn-primary" id="doAddProTpye">
        <br>
        <br>
        <div class="show-list text-center">
            <table class="table table-bordered table-hover" style='text-align: center;'>
                <thead>
                <tr class="text-danger">
                    <th class="text-center">编号</th>
                    <th class="text-center">类型名称</th>
                    <th class="text-center">状态</th>
                    <th class="text-center">操作</th>
                </tr>
                </thead>
                <tbody id="tb">
                <c:forEach items="${pageInfo.list}" var="productType">
                    <tr>
                        <td>${productType.id}</td>
                        <td>${productType.name}</td>
                        <td>
                            <c:if test="${productType.status==1}">启用状态</c:if>
                            <c:if test="${productType.status==0}">禁用状态</c:if>
                        </td>
                        <td class="text-center">
                            <input type="button" class="btn btn-warning btn-sm doProTypeModify" value="修改"
                                   onclick="findProductTypeById(${productType.id})">
                            <input type="button" class="btn btn-warning btn-sm doProTypeDelete" value="删除" onclick="deleteProdutType(${productType.id})">
                            <c:if test="${productType.status==1}"> <input type="button"
                                                                          class="btn btn-danger btn-sm doProTypeDisable"
                                                                          value="禁用" onclick="modifyStatus(${productType.id},this)"></c:if>
                            <c:if test="${productType.status==0}"> <input type="button"
                                                                          class="btn btn-success btn-sm doProTypeDisable"
                                                                          value="启动" onclick="modifyStatus(${productType.id},this)"></c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%--通过js加载分页插件--%>
            <ul id="pagination"></ul>
        </div>
    </div>
</div>

<!-- 添加商品类型 start -->
<div class="modal fade" tabindex="-1" id="ProductType">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">添加商品类型</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="productTypeName" class="col-sm-4 control-label">类型名称：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="productTypeName">
                    </div>
                </div>
                <br>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary addProductType" onclick="addProductType()">添加</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 添加商品类型 end -->

<!-- 修改商品类型 start -->
<div class="modal fade" tabindex="-1" id="myProductType">
    <!-- 窗口声明 -->
    <div class="modal-dialog modal-lg">
        <!-- 内容声明 -->
        <div class="modal-content">
            <!-- 头部、主体、脚注 -->
            <div class="modal-header">
                <button class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">修改商品类型</h4>
            </div>
            <div class="modal-body text-center">
                <div class="row text-right">
                    <label for="proTypeNum" class="col-sm-4 control-label">编号：</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="proTypeNum" readonly>
                    </div>
                </div>
                <br>
                <div class="row text-right">
                    <label for="proTypeName" class="col-sm-4 control-label">类型名称</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="proTypeName">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-warning updateProType" onclick="modifyName($('#proTypeNum').val(),$('#proTypeName').val())">修改</button>
                <button class="btn btn-primary cancel" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 修改商品类型 end -->
</body>

</html>