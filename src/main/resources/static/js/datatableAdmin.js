
var tableData;

$(document).ready( function () {

    // Loading table
    tableData = $('#adminTable').DataTable({
        order: [ [7, 'desc'] ],
        ajax: {
            url: "rest-all",
            dataSrc: ''
        },
        columns: [
            {"data": "name"},
            {"data": "surname"},
            {"data": "documentNumber"},
            {"data": "addressName"},
            {"data": "addressNumber"},
            {"data": "floor"},
            {"data": "phone"},
            {
                "data": "dateOfAdmission",
                render: function (data){
                    return data.substring(0, 10);
                }
            },
            {"data": "dateOfEgress"},
            {
                "data": null,
                "defaultContent": '<div class="d-grid d-inline-flex px-3">'+
                    '<a class="btn-edit btn btn-primary btn-sm mx-1" style="color:white"'+
                    'role="button" data-bs-toggle="button">Editar</a>'+
                    '<a class="btn-delete btn btn-danger btn-sm mx-1" style="color:white"'+
                    'role="button" data-bs-toggle="button">Eliminar</a>'+
                    '</div>',
                "orderable": false
            }
        ]
    });

    // Function Edit en evento click para redirección
    $('#adminTable').on('click', '.btn-edit', function (e) {
        e.preventDefault();
        var temp = $(this).parent().parent()[0];
        var data = tableData.row(temp).data();
        var url = "http://localhost:8080/sleepingMotorhome/admin/edit/" + data.id;
        $(location).attr('href', url);
    });

    // Function Delete en evento click para redirección
    $('#adminTable').on('click', '.btn-delete', function (e) {
        e.preventDefault();
        var temp = $(this).parent().parent()[0];
        var data = tableData.row(temp).data();
        var url = "http://localhost:8080/sleepingMotorhome/admin/delete/" + data.id;
        $(location).attr('href', url);
    } );

});