function openNav(){
    $("#sidenav").css("width","350px");
}
function closeNav(){
    $("#sidenav").css("width","0");
}
// ========== LEAFLET INITIALIZE ===========
/**
 * [y,x], zoomLevel
 */
var mymap = L.map('mapid').setView(L.latLng(38.9027, -77.0369), 14);

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoiZWRhdmlkaiIsImEiOiJjamZhbzd4MzkwN3ppMzNtaXo2ZWo2cjNhIn0.ehBPDgUGJhDT_DYwX8fOog', {
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
    maxZoom: 18,
    id: 'mapbox.streets',
    accessToken: 'your.mapbox.access.token'
}).addTo(mymap);
// ========= LEAFLET COMPONENTS ============
var heat, categoryContent;
$(document).ready(function(){ //once all elements have loaded calls this
    //use this method to send requests to the server adjusting url and changing the success function
    $.ajax({
        type:"GET",
        url:"/points",
        success: function(response){
            heat = L.heatLayer(response, {radius: 25}).addTo(mymap);
        }
    });
});
$(".ui.search").search({
    apiSettings:{
        url:"/categories?q={query}",
        onResponse: function(res){
            console.log(res);
            return {
                results: res
            }
        }
    },
    type:"category",
    showNoResults:"true"
});
function searchHandler(){

}