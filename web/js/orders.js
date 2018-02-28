function takeOrder(event) {
    var container = event.currentTarget;
    var orderId = parseInt(container.getAttribute('data-id'),10);

    var xmlHttp = null;
    if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlHttp.open("POST", "/takeOrder", true);
    xmlHttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                alert("Заказ принят");
                location.reload();

            }
        }
    };
    xmlHttp.send(orderId);
}

