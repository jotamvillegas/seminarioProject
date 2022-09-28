
var tableData;

$(document).ready( function () {

    // Loading table
    tableData = $('#taskTable').DataTable({
        order: [ [4, 'desc'] ],
        ajax: {
            url: "rest-all",
            dataSrc: ''
        },
        columns: [
            {data: "service.description", className: "text-center"},
            {data: "serviceType.description", className: "text-center"},
            {
                data: null,
                className: "text-center",
                render: function (data){
                    return data.employees[0].name + ' '+ data.employees[0].surname;
                }
            },
            {
                data: "amountHoursWeekly",
                className: "text-center",
                render: function (data, type){
                    return type === 'display' && data === 0 ? "" : data + ' hs';
                }
            },
            {data: "garage.garageNumber", className: "text-center"},
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
    $('#taskTable').on('click', '.btn-edit', function (e) {
        e.preventDefault();
        var temp = $(this).parent().parent()[0];
        var data = tableData.row(temp).data();
        var url = "http://localhost:8080/sleepingMotorhome/task/edit/" + data.id;
        $(location).attr('href', url);
    });

    // Function Delete en evento click para redirección
    $('#taskTable').on('click', '.btn-delete', function (e) {
        e.preventDefault();
        var temp = $(this).parent().parent()[0];
        var data = tableData.row(temp).data();
        var url = "http://localhost:8080/sleepingMotorhome/task/delete/" + data.id;
        $(location).attr('href', url);
    } );

});