//鼠标悬浮时，显示背景颜色
function showBGColor() {
    //event:当前发生的事件
    //event.srcElement:事件源
    // alert(event.srcElement);
    // alert(event.srcElement.tagName);//TD
    if(event && event.srcElement && event.srcElement.tagName == "TD"){
        var td = event.srcElement;
        //获取td的父元素
        var tr = td.parentElement;
        //通过js设置样式：tr.style
        tr.style.backgroundColor = "navy";
        //cells表示包含的所有单元格
        var tds = tr.cells;
        for(var i = 0; i < tds.length; i++){
            tds[i].style.color = "white";
        }
    }
}

function clearBGColor() {
    if(event && event.srcElement && event.srcElement.tagName == "TD"){
        var td = event.srcElement;
        //获取td的父元素
        var tr = td.parentElement;
        //通过js设置样式：tr.style
        tr.style.backgroundColor = "transparent";
        //cells表示包含的所有单元格
        var tds = tr.cells;
        for(var i = 0; i < tds.length; i++){
            tds[i].style.color = "cornflowerblue";
        }
    }
}

function showHand() {
    if(event && event.srcElement && event.srcElement.tagName == "TD"){
        var td = event.srcElement;
        td.style.cursor = "hand";
    }
}













