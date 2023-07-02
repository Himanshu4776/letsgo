import { useAtom } from "jotai";
import { useEffect, useRef } from "react";

import { googleMapAtom } from "./google-map.atoms";
import { LatLng } from "./google-map.types";
import { noop } from "./utils";

export interface GoogleMapMarkerProps {
  position: LatLng;
  onMarkerDragged?: (position: LatLng) => void;
}

export function GoogleMapMarker({
  position,
  onMarkerDragged = noop,
}: GoogleMapMarkerProps) {
  const markerRef = useRef<google.maps.Marker | undefined>(undefined);
  const [map] = useAtom(googleMapAtom);

  useEffect(() => {
    markerRef.current?.setPosition(position);
  }, [position]);

  useEffect(() => {
    let listenerRef: google.maps.MapsEventListener | undefined;
    if (window.google && window.google.maps) {
      markerRef.current = new google.maps.Marker({
        position,
        map,
        draggable: true,
      });
    }

    if (!listenerRef && markerRef.current) {
      listenerRef = markerRef.current.addListener("dragend", () => {
        const position = markerRef.current?.getPosition();
        if (position) {
          onMarkerDragged({
            lat: position.lat(),
            lng: position.lng(),
          });
        }
      });
    }

    return function () {
      if (listenerRef) {
        listenerRef.remove();
      }
    };
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [map, onMarkerDragged]);

  return <div></div>;
}
