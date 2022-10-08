
// VARIABLES

//var xValuesBar = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"]
var xValuesBar = ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"]
var yValuesBar = chartDataMotorhomes;
var yValuesBarSmall = chartDataMotorhomesSmall;
var yValuesBarMedium = chartDataMotorhomesMedium;
var yValuesBarLarge = chartDataMotorhomesLarge;

console.log("chartDataMotorhomes: " + chartDataMotorhomes)
console.log("chartDataMotorhomes Small: " + chartDataMotorhomesSmall)
console.log("chartDataMotorhomes Medium: " + chartDataMotorhomesMedium)
console.log("chartDataMotorhomes Large: " + chartDataMotorhomesLarge)

// START-REGION CHART BAR TOTAL MONTH

var ctxBar = document.getElementById("chartBar");

new Chart( ctxBar, {
    type: "bar",
    data: {
        labels: xValuesBar,
        datasets: [{
            fill: true,
            backgroundColor: "rgba(255, 87, 51, 1)",
            data: yValuesBar
        }]
    },
    options: {
        legend: {
            display: false
        },
        title:{
            display: true,
            text: ""
        },
        scales: {
            yAxes: [{ticks: {min: 0, max: 35}}],
        }
    }
});


// START-REGION CHART BAR TOTAL MONTH AND TYPE

var ctxBar = document.getElementById("chartBar2");

new Chart( ctxBar, {
    type: "bar",
    data: {
        labels: xValuesBar,
        datasets: [{
            fill: true,
            backgroundColor: "rgba(255, 195, 15, 1)",
            data: yValuesBarSmall
        }, {
            fill: true,
            backgroundColor: "rgba(255, 87, 51, 1)",
            data: yValuesBarMedium
        },{
            fill: true,
            backgroundColor: "rgba(199, 0, 57, 1)",
            data: yValuesBarLarge
        }]
    },
    options: {
        legend: {
            display: false
        },
        title:{
            display: true,
            text: ""
        },
        scales: {
            yAxes: [{ticks: {min: 0, max: 35}}],
        }
    }
});
