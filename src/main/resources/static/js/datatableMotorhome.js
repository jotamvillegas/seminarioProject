
var tableData;

$(document).ready( function () {

    // Loading table
    tableData = $('#motorhomeTable').DataTable({
        order: [ [8, 'desc'] ],
        ajax: {
            url: "rest-all",
            dataSrc: ''
        },
        columns: [
            {
                data: null,
                className: "text-center",
                render: function (data){
                    return data.user.name + ' '+ data.user.surname;
                }
            },
            {data: "enrollment", className: "text-center"},
            {
                data: "lengthMotorhome",
                className: "text-center",
                render: function (data, type){
                    return type === 'display' && data === 0 ? "" : data + ' mts';
                }
            },
            {
                data: "widthMotorhome",
                className: "text-center",
                render: function (data, type){
                    return type === 'display' && data === 0 ? "" : data + ' mts';
                }
            },
            {data: "motorhomeType.motorhomeType", className: "text-center"},
            {data: "garage.garageNumber", className: "text-center"},
            {data: "garage.zone.zoneName", className: "text-center"},
            {
                data: "rentalDays",
                className: "text-center",
                render: function (data){
                    return data + ' días';
                }
            },
            {
                data: "dateOfAdmission",
                className: "text-center",
                render: function (data){
                    return data.substring(0, 10);
                }
            },
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
    $('#motorhomeTable').on('click', '.btn-edit', function (e) {
        e.preventDefault();
        var temp = $(this).parent().parent()[0];
        var data = tableData.row(temp).data();
        var url = "http://localhost:8080/sleepingMotorhome/motorhome/edit/" + data.id;
        $(location).attr('href', url);
    });

    // Function Delete en evento click para redirección
    $('#motorhomeTable').on('click', '.btn-delete', function (e) {
        e.preventDefault();
        var temp = $(this).parent().parent()[0];
        var data = tableData.row(temp).data();
        var url = "http://localhost:8080/sleepingMotorhome/motorhome/delete/" + data.id;
        $(location).attr('href', url);
    } );

});