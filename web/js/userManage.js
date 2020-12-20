var total; //记录总条数
var pageSize = "10"; //每页大小
var pageNumber = "1"; //当前页数
var pageCount; //总页数
var sort = "userName"; //排序字段
var sortOrder = "desc"; //升序降序

$(document).ready(function() {
    reload(); //加载数据
    //为动态添加的元素绑定事件
    $("tbody").on("mouseover", "tr", function() {
        $(this).addClass('tr_hover'); //通过jQuery控制实现鼠标悬停上的背景色
    });
    $("tbody").on("mouseout", "tr", function() {
        $(this).removeClass('tr_hover'); //通过jQuery控制实现鼠标悬停上的背景色
    });
    //tbody中的复选框绑定click事件
    $("tbody").on("click", " tr input:checkbox", function() {
        if (this.checked == true) {
            $(this).parents("tr").addClass('tr_select');
        } else {
            $(this).parents("tr").removeClass('tr_select');
        }
    });
    //绑定动态生成的表格中每行的删除按钮事件
    $('table').on('click', '#btnDel', function() {
        var userName = $(this).attr("value");
        $.ajax({
            type: "post",
            url: "DeleteUserServlet",
            data: { ids: userName },
            dataType: "json",
            success: function(response) {
                alert(response.info);
                if (response.code == 0) {
                    reload();
                }
            }
        });
    });

    //绑定动态生成的表格中每行的修改按钮事件
    $('table').on('click', '#btnUpdate', function() {
        var rowData = $(this).parents("tr").attr("data");
        openEditDiv(rowData);
    });

    //工具栏修改按钮点击
    $("#btUpdate").click(function() {
        var len = $('tbody tr input:checkbox:checked').length;
        if (len == 0) {
            alert("请选择一项！");
            return;
        }
        if (len != 1) {
            alert("只能选择一项！");
            return;
        }
        var rowData = $('tbody tr input:checkbox:checked').parents("tr").attr("data");
        openEditDiv(rowData);
    });

    // 绑定分页操作的按钮点击事件
    $("#first,#back,#next,#last").click(pageNation);
    // 绑定标题栏排序列点击事件
    $(".bg,.asc,.desc").click(funSort);
    //绑定标题栏的复选框单击事件
    $("#ckAll").click(function() {
        if (this.checked == true) { //判断是否被选择
            $("tbody tr input:checkbox").prop("checked", true);
            $("tbody tr").addClass('tr_select');
        } else {
            $("tbody tr input:checkbox").prop("checked", false);
            $("tbody tr").removeClass('tr_select');
        }
    });

    $("#btSearch").click(function() {
        reload(); //加载数据
    });

    $("#btClear").click(function() {
        document.getElementById("searchForm").reset();
        reload(); //加载数据
    });

    //按钮批量删除
    $("#btDelete").click(function() {
        var len = $('tbody tr input:checkbox:checked').length;
        if (len == 0) {
            alert("至少需要选择一项！");
            return;
        }
        var vals = []; //定义数组
        $('tbody tr input:checkbox:checked').each(function(index, item) {
            vals.push($(this).val()); //循环将选择复选框的value值加入数组中
        });

        $.ajax({
            type: "post",
            url: "deleteUser.do",
            data: { ids: vals.join(",") }, //使用，分隔符将数组所有数据拼接成一个字符串
            dataType: "json",
            success: function(response) {
                alert(response.info);
                if (response.code == 0) {
                    reload();
                }
            }
        });
    });

    //增加
    $("#btAdd").click(function() {
        FormUtils.emptyForm("registerForm");
        $("#formTitle").html("用户新增");
        $("#userName").attr("readonly", false);
        $("#action").val("insert");

        userName_correct = false;
        chrName_correct = false;
        mail_correct = false;
        province_correct = false;
        city_correct = false;
        password_correct = false;
        password1_correct = false;

        $("#userName").blur(checkUserName); //移除用户名的blur事件
        $("#mail").unbind("blur").blur(checkMail); //重新绑定邮箱的blur事件为全部检查

        ShowDiv('MyDiv', 'fade');
    });


});


function reload() {
    var queryParams = new Object(); //表单查询参数
    var array = $("#searchForm").serializeArray();
    $.each(array, function(index, element) {
        queryParams[element.name] = element.value;
    });

    var pageParams = new Object(); //分页参数
    pageSize = $("#pageSize").find("option:selected").text(); //每页大小
    pageParams.pageSize = pageSize;
    pageParams.pageNumber = pageNumber;
    pageParams.sort = sort;
    pageParams.sortOrder = sortOrder;

    var queryData = new Object(); //提交的数据
    queryData.queryParams = JSON.stringify(queryParams);
    queryData.pageParams = JSON.stringify(pageParams);

    $.ajax({
        type: "post",
        url: "SelectUserServlet",
        data: queryData,
        dataType: "json",
        success: function(response) {
            var rows = response.rows;
            total = response.total;
            pageCount = Math.ceil(total / pageSize); //计算总页数
            $("#total").text(total);
            $("#pageCount").text(pageCount); //总页数
            $("tbody").empty();
            $.each(rows, function(index, row) {
                var s = JSON.stringify(row);
                var str = "<tr data='" + s + "'>";
                str = str + '<td><input type="checkbox" value=' + row.userName + ' /></td>';
                str = str + '<td>' + row.userName + '</td>';
                str = str + '<td>' + row.chrName + '</td>';
                str = str + '<td>' + row.mail + '</td>';
                str = str + '<td>' + row.provinceName + '</td>';
                str = str + ' <td>' + row.cityName + '</td>';
                str = str + ' <td><a href = "#" id="btnDel" value=' + row.userName + '>删除</a> ';
                str = str + '<a href="#" id="btnUpdate">修改</a></td>';
                str = str + ' </tr>';
                $("tbody").append(str);
            });
            //通过jQuery控制表格隔行换色，并鼠标悬停变色
            $('tbody tr:even').addClass('tr_even'); //默认偶数行背景色
            $('tbody tr:odd').addClass('tr_odd'); //默认奇数数行背景色
        }
    });
}

// 分页操作的函数
var pageNation = function() {
    // 处理翻页的操作
    var idValue = $(this).attr("id");
    if ("first" == idValue) {
        pageNumber = 1;
    } else if ("back" == idValue) {
        if (pageNumber > 1) {
            pageNumber--;
        }
    } else if ("next" == idValue) {
        if (pageNumber < pageCount) {
            pageNumber++;
        }
    } else if ("last" == idValue) {
        pageNumber = pageCount;
    }
    pageNumber = pageNumber.toString(); //转换为字符串
    reload();
}

//点击排序字段
var funSort = function() {
    sort = $(this).attr("data");
    if (sort == "userName") {
        $("#sortByProvince").removeClass().addClass("bg");
    } else if (sort == "province") {
        $("#sortByUserName").removeClass().addClass("bg");
    }

    var className = $(this).attr("class");
    if (className == "bg") {
        $(this).removeClass().addClass("asc");
        sortOrder = "asc";
    } else if (className == "asc") {
        $(this).removeClass().addClass("desc");
        sortOrder = "desc"
    }
    if (className == "desc") {
        $(this).removeClass().addClass("asc");
        sortOrder = "asc";
    }

    reload();
}


//弹出隐藏层
function ShowDiv(show_div, bg_div) {
    document.getElementById(show_div).style.display = "block";
    document.getElementById(bg_div).style.display = "block";
    //设置背景层高度宽度
    // var bgdiv = document.getElementById(bg_div);
    // bgdiv.style.width = $(window).width();
    // $("#" + bg_div).height($(window).height());

    //弹出层居中
    var windowHeight = $(window).height(); //获取当前窗口高度
    var windowWidth = $(window).width(); //获取当前窗口宽度
    var popupHeight = $("#" + show_div).height(); //获取弹出层高度
    var popupWeight = $("#" + show_div).width(); //获取弹出层宽度
    var posiTop = (windowHeight - popupHeight) / 2;
    var posiLeft = (windowWidth - popupWeight) / 2;
    $("#" + show_div).css({ "left": posiLeft + "px", "top": posiTop + "px", "display": "block" }); //设置position
}
//关闭弹出层
function CloseDiv(show_div, bg_div) {
    document.getElementById(show_div).style.display = "none";
    document.getElementById(bg_div).style.display = "none";
}


var FormUtils = {
    fillForm: function(formid, data) {
        $('#' + formid).find(':input').each(function() {
            $(this).val(data[$(this).attr('id')]);
        });
    },
    emptyForm: function(formid) {
        $('#' + formid).find(':input').each(function() {
            $(this).val("");
        });
        $('#' + formid).find('span').each(function() {
            $(this).html("");
        });
    }
};

function openEditDiv(rowData) {
    var data = JSON.parse(rowData);
    FormUtils.emptyForm("registerForm");
    FormUtils.fillForm("registerForm", data);
    fillCity(data['cityCode']);
    $("#password1").val(data['password']);

    $("#formTitle").html("用户修改");
    $("#userName").attr("readonly", true); //设置用户名只读
    $("#userName").off("blur"); //移除用户名的blur事件
    $("#mail").unbind("blur").blur(checkMailFormat); //重新绑定邮箱的blur事件为只需检查格式合法性
    userName_correct = true;
    $('#chrName').blur();
    $("#mail").blur();
    $("#password").blur();
    $("#password1").blur();
    $("#provinceCode").blur();
    $("#action").val("update");
    ShowDiv('MyDiv', 'fade');
}