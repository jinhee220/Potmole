<template>
    <div class="map">
      <!-- The map container -->
      <div ref="map" class="map-container"></div>
    </div>
  </template>
  
  <script>
      // Include Mapbox GL JS library from node-module after running npm install mapbox-gl in terminal
  import mapboxgl from 'mapbox-gl'
  // Include Mapbox Geocoder plugin from node-module after running npm install @mapbox/mapbox-gl-geocoder in terminal
  import MapboxGeocoder from '@mapbox/mapbox-gl-geocoder'


  export default {
    mounted() {
      // Include Mapbox GL JS library from node-module after running npm install mapbox-gl in terminal
      //const mapboxgl = require('mapbox-gl');
      // Include Mapbox Geocoder plugin from node-module after running npm install @mapbox/mapbox-gl-geocoder in terminal
      //const MapboxGeocoder = require('@mapbox/mapbox-gl-geocoder');
  
      // Set your Mapbox access token
      mapboxgl.accessToken = 'pk.eyJ1IjoidGVzdHltY3Rlc3RmYWNlIiwiYSI6ImNsdW1wcjE5NjB3aGUyaW1scGVtb2R4YjIifQ._vhnmLy-i4syL82KbkdyDA';
  
      // Create the map instance
      const map = new mapboxgl.Map({
        
        container: this.$refs.map,
        style: 'mapbox://styles/mapbox/streets-v12',
        center: [-79.4512, 43.6568],
        zoom: 13,
      });
  
      // Add the geocoder control to the map
      map.addControl(
        new MapboxGeocoder({

          accessToken: mapboxgl.accessToken,
          mapboxgl: mapboxgl,
        })
      );
    }
  };
  </script>
  
  <style scoped>
  .map {
    width: 100%;
    height: 400px;
  }
  .map-container {
    width: 100%;
    height: 100%;
  }
  </style>
  