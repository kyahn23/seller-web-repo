$(function () {
    var currentPath = window.location.pathname
    $('#LNB>li>ul>li>[href="' + currentPath + '"]').parent().addClass('current')
})