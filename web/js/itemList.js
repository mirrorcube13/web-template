window.onload= function() {
    document.getElementById('toggler').onclick = function() {
        openbox('block', this);
        return false;
    };
};
function openbox(classname, toggler) {
    var arr = document.getElementsByClassName(classname);
    Array.prototype.forEach.call(arr, function(div) {
        if(div.style.display == 'block') {
        div.style.display = 'none';
    }
else {
        div.style.display = 'block';
    }
});
}


