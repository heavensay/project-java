<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="expires" content="0"/>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
    <meta http-equiv="description" content="This is my page"/>

    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.3.2/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.3.2/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.3.2/demo.css"/>


    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
</head>

<body class="easyui-layout">
<div data-options="region:'north',border:false"
     style="height: 60px; background: #B3DFDA; padding: 10px">
    north region
</div>
<div data-options="region:'west',split:true,title:'West'"
     style="width: 150px; padding: 10px;">
    west content
    <div>
        123
        <ul id="resource-menu-list"></ul>
    </div>
</div>
<div
        data-options="region:'east',split:true,collapsed:true,title:'East'"
        style="width: 100px; padding: 10px;">
    east region
</div>
<div data-options="region:'south',border:false"
     style="height: 50px; background: #A9FACD; padding: 10px;">
    south region
</div>
<div data-options="region:'center',title:'Center'">
    <div class="easyui-tabs" data-options="fit:true,border:false">
        <div title="Tab1" style="padding: 20px; overflow: hidden;">
            <div style="margin-top: 20px;">
                <h3>
                    jQuery EasyUI框架帮助您构建您的web页面很容易。
                </h3>
                <ul>
                    <li>
                        easyui是一组基于jQuery的用户界面插件。
                    </li>
                    <li>
                        使用easyui你不写很多javascript代码,而你也不用通过编写HTML标签来定义用户界面。
                    </li>
                    <li>
                        easyui非常简单但强大的。
                    </li>
                </ul>
            </div>
        </div>
        <div title="Tab2" data-options="closable:true"
             style="padding: 20px;">
            This is Tab2 width close button.
        </div>
        <div title="Tab3" data-options="iconCls:'icon-reload',closable:true"
             style="overflow: hidden; padding: 5px;">
            <table id="tt2">tatat2</table>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">

    var leftResource = [{
        "id": 1,
        "text": "用户权限",
        "state": "open",
        "checked": false,
        "attributes": null,
        "children": [{
            "id": 3,
            "text": "用户插入",
            "state": "open",
            "checked": false,
            "attributes": null,
            "children": [{
                "id": 4,
                "text": "用户插入批量",
                "state": "open",
                "checked": false,
                "attributes": null,
                "children": []
            }]
        }, {
            "id": 2,
            "text": "用户查询",
            "state": "open",
            "checked": false,
            "attributes": null,
            "children": []
        }]
    }]

    function loadDataGrid() {
        $('#dg').datagrid({
            data: datagridjson,
            columns: [[{
                field: 'id',
                title: 'ID',
                width: 100
            }, {
                field: 'name',
                title: 'Name',
                width: 100
            }, {
                field: 'comment',
                title: 'comment',
                width: 100,
                align: 'right'
            }]]

        });
    }

    var datagridjson = [{
        "id": 1,
        "name": "tom",
        "comment": "c1"
    }, {
        "id": 2,
        "name": "jerry",
        "comment": "c2"
    }];

    /*******    ****/
    var treejson;
    $(document).ready(function () {
        $.ajax({
            url: "user/queryResourceTree?name=admin",
            type: "get",
            async: false,
            success: function (data, textStatus, jqXHR) {
                treejson = JSON.parse('[' + JSON.stringify(data) + ']');
            }
        });

        var useresource;
        $.ajax({
            url: "user/queryUserPermission?name=admin",
            type: "get",
            async: false,
            success: function (data, textStatus, jqXHR) {
                useresource = data;
                //useresource = JSON.parse('[' + JSON.stringify(data) + ']');
            }
        });

        var f = function (obj) {
            $.each(obj, function (index, node) {
                var isnotcontain;
                if (node.children) {
                    f(node.children);
                }
                isnotcontain = f2(node.id);

                if (isnotcontain) {
                    console.log(node.id + ' is not permission');
                }


            });
        }
        var f2 = function (id) {
            var isnotcontain = true;

            $.each(useresource, function (i, n) {
                if (!isnotcontain) {
                    return isnotcontain;
                }

                if (id == n.id) {
                    isnotcontain = false;
                    return isnotcontain;
                }
                if (n.children) {
                    f2(id);
                }

                isnotcontain = true;
                return isnotcontain;
            });
            return isnotcontain;
        }

        f(leftResource);

        $("#resource-menu-list").tree({
            data: treejson,
            async: false,
            method: "get",

            error: function () {
                alert('error');
            },
            onClick: function (node) {
                if (node.attributes.url) {
                    loadDataGrid();
                }
            }
        });
    });
</script>

</html>
