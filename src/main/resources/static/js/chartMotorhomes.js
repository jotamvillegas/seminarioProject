
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
            data: yValuesBar,
            fill: true,
            backgroundColor: [
                'rgba(255, 87, 51, 2)',
                'rgba(255, 26, 104, 2)',
                'rgba(54, 162, 235, 2)',
                'rgba(255, 206, 86, 2)',
                'rgba(75, 192, 192, 2)',
                'rgba(153, 102, 255, 2)',
                'rgba(185, 239, 108, 2)',
                'rgba(243, 124, 240, 2)',
                'rgba(124, 243, 225, 2)',
                'rgba(247, 28, 28, 2)',
                'rgba(53, 115, 247, 2)',
                'rgba(250, 177, 83, 2)'
            ]
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
            label: "Small",
            fill: true,
            backgroundColor: "rgba(175, 122, 197, 2)",
            data: yValuesBarSmall
        }, {
            label: "Medium",
            fill: true,
            backgroundColor: "rgba(84, 153, 199, 2)",
            data: yValuesBarMedium
        },{
            label: "Large",
            fill: true,
            backgroundColor: "rgba(72, 201, 176, 2)",
            data: yValuesBarLarge
        }]
    },
    options: {
        legend: {
            display: true,
            labels: {
                color: 'rgb(255, 99, 132)'
            }
        },
        /*plugins: {
            legend: {
                position: 'right',
            }
        },*/
        /*title:{
            display: true,
            text: ""
        },*/
        scales: {
            yAxes: [{ticks: {min: 0, max: 35}}],
        }
    }
});
