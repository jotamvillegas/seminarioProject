
var tableData;

$(document).ready( function () {

    // Loading table
    tableData = $('#zoneTable').DataTable({
        order: [ [1, 'asc'] ],
        ajax: {
            url: "rest-all",
            dataSrc: ''
        },
        columns: [
            {data: "id", className: "text-center"},
            {data: "zoneName", className: "text-center"},
            {data: "motorhomeType.motorhomeType", className: "text-center"},
            {data: "garageAmount", className: "text-center"},
            {
                data: null,
                className: "text-center",
                defaultContent: '<div class="d-grid d-inline-flex px-3">'+
                    '<a class="btn-edit btn btn-primary btn-sm mx-1" style="color:white"'+
                    'role="button" data-bs-toggle="button">Editar</a>'+
                    '<a class="btn-delete btn btn-info btn-sm mx-1" style="color:white"'+
                    'role="button" data-bs-toggle="button">Eliminar</a>'+
                    '</div>',
                orderable: false
            }
        ]
    });

    // Function Edit en evento click para redirección
    $('#zoneTable').on('click', '.btn-edit', function (e) {
        e.preventDefault();
        var temp = $(this).parent().parent()[0];
        var data = tableData.row(temp).data();
        var url = "http://localhost:8080/sleepingMotorhome/zone/edit/" + data.id;
        $(location).attr('href', url);
    });

    // Function Delete en evento click para redirección
    $('#zoneTable').on('click', '.btn-delete', function (e) {
        e.preventDefault();
        var temp = $(this).parent().parent()[0];
        var data = tableData.row(temp).data();
        var url = "http://localhost:8080/sleepingMotorhome/zone/delete/" + data.id;
        $(location).attr('href', url);
    } );

});