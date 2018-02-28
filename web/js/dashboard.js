window.onload = function () {
    document.getElementById('customers').onclick = function () {
        var zzz = document.getElementById('customers');
        if (zzz.className == "tt open") {
            $('#customersBlock').hide();
            zzz.className = 'tt closed';
        } else {
            $('#ordersBlock').hide();
            $('#itemsBlock').hide();
            $('#customersBlock').show();
            zzz.className = 'tt open';

        }
    };
    document.getElementById('orders').onclick = function () {
        var zzz = document.getElementById('orders');
        if (zzz.className == "tt open") {
            $('#ordersBlock').hide();
            zzz.className = 'tt closed';
        } else {
            $('#customersBlock').hide();
            $('#itemsBlock').hide();
            $('#ordersBlock').show();
            zzz.className = 'tt open';
        }
    };
    document.getElementById('items').onclick = function () {
        var zzz = document.getElementById('items');
        if (zzz.className == "tt open") {
            $('#itemsBlock').hide();
            zzz.className = 'tt closed';
        } else {
            $('#customersBlock').hide();
            $('#ordersBlock').hide();
            $('#itemsBlock').show();
            zzz.className = 'tt open';
        }
    };

};



