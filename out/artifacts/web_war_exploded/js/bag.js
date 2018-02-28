function sum() {
    var sum = document.getElementsByClassName('sum')[0];
    var items = document.getElementsByClassName('counter');
    var index;
    var price = 0;
    for (index = 0; index < items.length; ++index) {
        price += (parseInt(items[index].getAttribute('data-price')) * $(items[index]).val());
    }
    sum.innerHTML = price;
}

function sendItems() {
    var products = {items: []};
    var items = document.getElementsByClassName('counter');
    var index;
    for (index = 0; index < items.length; ++index) {
        products.items.push({
            id: parseInt(items[index].getAttribute('data-id')),
            amount: parseInt($(items[index]).val())
        });
    }
    var xmlHttp = null;
    if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    var toServer = JSON.stringify(products);
    xmlHttp.open("POST", "/shoppingBag", true);
    xmlHttp.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xmlHttp.onreadystatechange = function () {
        if (xmlHttp.readyState == 4) {
            if (xmlHttp.status == 200) {
                var str = xmlHttp.responseText;
                if (str) {
                    alert("Ваш заказ оформлен");
                    location.reload();
                } else {
                    alert("Ошибка");
                }
            }
        }
    };
    xmlHttp.send(toServer);
}

function deleteItem(event) {
    var item = event.currentTarget;
    item.parentNode.remove();
    sum();
}

$(document).ready(function () {
    $('.caption').on('click', function (evt) {
        var elem = evt.target;
        var container = evt.currentTarget;
        var input = container.getElementsByClassName('counter')[0];
        var sum = document.getElementsByClassName('sum')[0];
        var price = parseInt(input.getAttribute('data-price'), 10);
        if ($(input).data('old-value') == null) {
            if ($(input).val() == 2)
                sum.innerHTML = parseInt(sum.firstChild.nodeValue, 10) + price;
        }
        else {
            sum.innerHTML = parseInt(sum.firstChild.nodeValue, 10) + price * ($(input).val() - $(input).data('old-value'));
        }
        $(input).data('old-value', $(input).val());
    });
});

