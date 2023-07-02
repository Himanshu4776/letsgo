import { LoaderOptions } from "@googlemaps/js-api-loader";
import { useAtom } from "jotai";
import { useCallback, useEffect, useRef } from "react";

import { googleMapAtom } from "./google-map.atoms";
import { LatLng } from "./google-map.types";
import { useLoadGoogleApi } from "./use-load-google-api";
import { noop } from "./utils";

export interface GoogleMapProps {
  apiKey: string;
  defaultCenter: LatLng;
  children: React.ReactNode;
  position?: LatLng;
  center?: LatLng;
  id?: string;
  defaultZoom?: number;
  loaderOptions?: Partial<LoaderOptions>;
  onLoadError?: (error: unknown) => void;
  onInitialized?: () => void;
}

export function GoogleMap({
  apiKey,
  defaultCenter,
  id = "map",
  defaultZoom = 8,
  loaderOptions = {},
  children,
  center,
  position,
  onLoadError = console.error,
  onInitialized = noop,
}: GoogleMapProps) {
  const mapRef = useRef(null);
  const [map, setMap] = useAtom(googleMapAtom);
  const { apiLoaded } = useLoadGoogleApi({
    apiKey,
    loaderOptions,
    onLoadError,
  });

  const initializeMap = useCallback(() => {
    if (!window.google) {
      throw new Error("Google script not loaded");
    }
    if (!window.google.maps) {
      throw new Error("Google Maps script not loaded");
    }
    if (mapRef.current) {
      const map = new window.google.maps.Map(mapRef.current, {
        center: defaultCenter,
        zoom: defaultZoom,
        disableDefaultUI: true,
      });
      setMap(map);
    }
  }, [defaultCenter, defaultZoom, setMap]);

  useEffect(() => {
    if (apiLoaded) {
      initializeMap();
      onInitialized();
    }
  }, [apiLoaded, initializeMap, onInitialized]);

  useEffect(() => {
    if (map && center) {
      map.setCenter(center);
    }
  }, [center, map]);

  useEffect(() => {
    if (map && position && position.lat && position.lng) {
      map.panTo(position);
    }
  }, [position, map]);

  return (
    <div id={id} ref={mapRef} className="w-full h-64">
      {children}
    </div>
  );
}
