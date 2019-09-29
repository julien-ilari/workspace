// Appel dataTables jQuery plugin
$(document).ready(function () {
    $('#dataTable').DataTable({
        language: {
            url: "/resources/js/demo/French.json"
        }
    });

});