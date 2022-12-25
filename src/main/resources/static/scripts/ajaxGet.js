var servResponse = document.querySelector('.content');

document.forms.qwe.onchange = function(q) {
    q.preventDefault();

    var type = encodeURIComponent(document.forms.qwe.type);

    var xhr = new XMLHttpRequest();

    xhr.open('GET', '/articles?' + 'type=' + type, true);
    xhr.send();

    xhr.onreadystatechange = function () {
        if(xhr.readyState === 4 && xhr.status === 200){
            servResponse.textContent = xhr.responseText;
        }
    }
}