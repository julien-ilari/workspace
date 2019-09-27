// Appel dataTables jQuery plugin


$(document).ready(function () {
    $('#dataTable').DataTable({
        language: {
            url: "js/demo/French.json"
        }
    });

});