import { useCallback, useEffect, useRef, useState } from "react";
import { GoogleMap } from "./google-map/google-map";
import "./index.css";
import { LatLng } from "./google-map";
import { GoogleMapMarker } from "./google-map/google-map-marker";

const center = { lat: 0, lng: 0 };

function App() {
  const geocoderRef = useRef<google.maps.Geocoder | undefined>(undefined);
  const [markerPosition, setMarkerPosition] = useState<LatLng>({
    lat: 0,
    lng: 0,
  });

  const handleMapInitialized = useCallback(() => {
    geocoderRef.current = new google.maps.Geocoder();
  }, []);

  const [addressPosition, setAddressPosition] = useState<LatLng>({
    lat: 0,
    lng: 0,
  });

  useEffect(() => {
    navigator.geolocation?.getCurrentPosition(
      function (position) {
        center.lat = position.coords.latitude;
        center.lng = position.coords.longitude;
        setMarkerPosition(center);
        setAddressPosition(center);
      },
      (error) => {
        setMarkerPosition(center);
        setAddressPosition(center);
      }
    );
  }, []);

  return (
    <div>
      <p className="bg-cyan-500">Click button and open the map</p>
      <GoogleMap
        apiKey="AIzaSyDgI5Di2NQkVMU3Zxy__aLl9gbJkvMKJbA"
        defaultCenter={center}
        position={addressPosition}
        onInitialized={handleMapInitialized}
        defaultZoom={14}
      >
        <GoogleMapMarker position={markerPosition} onMarkerDragged={() => {}} />
      </GoogleMap>
    </div>
  );
}

export default App;

// letsgo-391610.
