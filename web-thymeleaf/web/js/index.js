function $(id) {
    return document.getElementById(id);
}

window.onload = function () {
    //页面加载完成绑定事件
    //根据id获取html对象
    var fruitTbl = $("tbl_fruit");
    //获取所有行
    var rows = fruitTbl.rows;
    for (var i = 1; i < rows.length - 1; i++) {
        var tr = rows[i];
        trBindEvent(tr);
    }
    $("add").onclick = addFruit;
}

function trBindEvent(tr) {
    //1.绑定鼠标悬浮设置颜色事件
    tr.onmouseover = showBGColor;
    tr.onmouseout = clearBGColor;
    //2.获取所有单元格，绑定单价单元格设置指针事件
    var cells = tr.cells;
    var priceTD = cells[1];
    priceTD.onmouseover = showHand;
    //3.修改价格
    priceTD.onclick = editPrice;
    //4.删除行
    var img = cells[4].firstChild;
    img.onclick = delFruit;
}

function addFruit() {
    var fname = $("fname").value;
    var fprice = parseInt($("fprice").value);
    var fcount = parseInt($("fcount").value);
    var xj = fprice * fcount;

    var fruitTbl = $("tbl_fruit");
    var tr = fruitTbl.insertRow(fruitTbl.rows.length - 1);

    var fnameTD = tr.insertCell();
    fnameTD.innerText = fname;
    var fpriceTD = tr.insertCell();
    fpriceTD.innerText = fprice;
    var fcountTD = tr.insertCell();
    fcountTD.innerText = fcount;
    var xjTD = tr.insertCell();
    xjTD.innerText = xj;
    var delTD = tr.insertCell();
    delTD.innerHTML = "<img src='../imgs/del.jpg' class='del_img'>";

    trBindEvent(tr);
    updateZJ();

}

function delFruit() {
    if (event && event.srcElement && event.srcElement.tagName == "IMG") {
        if (window.confirm("是否确认删除当前库存记录？")) {
            var img = event.srcElement;
            var tr = img.parentElement.parentElement;
            var fruitTbl = document.getElementById("tbl_fruit");
            fruitTbl.deleteRow(tr.rowIndex);
            updateZJ();
        }
    }
}

function editPrice() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var priceTD = event.srcElement;
        //判断是否为文本，第二次点击为input类型非文本
        if (priceTD.firstChild && priceTD.firstChild.nodeType == 3) {
            var oldPrice = priceTD.innerText;
            //设置内部html代码
            priceTD.innerHTML = "<input type='text' size='4'>";
            var input = priceTD.firstChild;
            if (input.tagName == "INPUT") {
                input.value = oldPrice;
                //默认全选
                input.select();
                //失去焦点
                input.onblur = updatePrice;
                //检查输入
                input.onkeydown = ckInput;
            }
        }
    }
}

function ckInput() {
    //0~9 : 48-57
    //backspace : 8
    //enter : 13
    var kc = event.keyCode;
    if (!((kc >= 48 && kc <= 57) || kc == 8 || kc == 13)) {
        event.returnValue = false;
    }
    if (kc == 13) {
        event.srcElement.blur();
    }
}

function updatePrice() {
    if (event && event.srcElement && event.srcElement.tagName == "INPUT") {
        var input = event.srcElement;
        var newPrice = input.value;
        var priceTD = input.parentElement;
        priceTD.innerText = newPrice;

        //更新小计
        updateXJ(priceTD.parentElement);//TR
    }
}

function updateXJ(tr) {
    if (tr && tr.tagName == "TR") {
        var tds = tr.cells;
        var price = tds[1].innerText;
        var count = tds[2].innerText;
        tds[3].innerText = parseInt(price) * parseInt(count);

        //更新总计
        updateZJ();
    }
}

function updateZJ() {
    var fruitTbl = document.getElementById("tbl_fruit");
    var rows = fruitTbl.rows;
    var sum = 0;
    for (var i = 1; i < rows.length - 1; i++) {
        var tr = rows[i];
        var xj = parseInt(tr.cells[3].innerText);
        sum = sum + xj;
    }
    rows[rows.length - 1].cells[1].innerText = sum;
}

//鼠标悬浮时，显示背景颜色
function showBGColor() {
    //event:当前发生的事件
    //event.srcElement:事件源
    // alert(event.srcElement);
    // alert(event.srcElement.tagName);//TD
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;
        //获取td的父元素
        var tr = td.parentElement;
        //通过js设置样式：tr.style
        tr.style.backgroundColor = "navy";
        //cells表示包含的所有单元格
        var tds = tr.cells;
        for (var i = 0; i < tds.length; i++) {
            tds[i].style.color = "white";
        }
    }
}

function clearBGColor() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;
        //获取td的父元素
        var tr = td.parentElement;
        //通过js设置样式：tr.style
        tr.style.backgroundColor = "transparent";
        //cells表示包含的所有单元格
        var tds = tr.cells;
        for (var i = 0; i < tds.length; i++) {
            tds[i].style.color = "cornflowerblue";
        }
    }
}

function showHand() {
    if (event && event.srcElement && event.srcElement.tagName == "TD") {
        var td = event.srcElement;
        td.style.cursor = "hand";
    }
}