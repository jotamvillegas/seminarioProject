
var tableData;

$(document).ready( function () {

    // Loading table
    tableData = $('#garageTable').DataTable({
        order: [ [0, 'asc'] ],
        ajax: {
            url: "rest-all",
            dataSrc: ''
        },
        columns: [
            {data: "zone.zoneName", className: "text-center"},
            {data: "garageNumber", className: "text-center"},
            {
                data: "garageStatus",
                className: "text-center",
                render: function (data){
                    return data === true ? "Ocupado" : 'Libre';
                }
            },
            {
                data: "dateOfAdmission",
                className: "text-center",
                render: function (data){
                    return data === null ? "" : data.substring(0, 10);
                }
            },
            {
                data: "dateOfEgress",
                className: "text-center",
                render: function (data){
                    return data === null ? "" : data.substring(0, 10);
                }
            },
            {
                data: null,
                className: "text-center",
                defaultContent: '<div class="d-grid d-inline-flex px-3">'+
                    /*'<a class="btn-edit btn btn-primary btn-sm mx-1" style="color:white"'+
                    'role="button" data-bs-toggle="button">Editar</a>'+*/
                    '<a class="btn-delete btn btn-info btn-sm mx-1" style="color:white"'+
                    'role="button" data-bs-toggle="button">Eliminar</a>'+
                    '</div>',
                orderable: false
            }
        ]
    });

    // Function Edit en evento click para redirección
    $('#garageTable').on('click', '.btn-edit', function (e) {
        e.preventDefault();
        var temp = $(this).parent().parent()[0];
        var data = tableData.row(temp).data();
        var url = "http://localhost:8080/sleepingMotorhome/garage/edit/" + data.id;
        $(location).attr('href', url);
    });

    // Function Delete en evento click para redirección
    $('#garageTable').on('click', '.btn-delete', function (e) {
        e.preventDefault();
        var temp = $(this).parent().parent()[0];
        var data = tableData.row(temp).data();
        var url = "http://localhost:8080/sleepingMotorhome/garage/delete/" + data.id;
        $(location).attr('href', url);
    } );

});