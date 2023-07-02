import { Libraries, Loader, LoaderOptions } from "@googlemaps/js-api-loader";
import { useEffect, useState } from "react";

export interface UseLoadGoogleApiProps {
  apiKey: string;
  libraries?: Libraries;
  loaderOptions?: Partial<LoaderOptions>;
  onLoadError?: (error: unknown) => void;
}

export function useLoadGoogleApi({
  apiKey,
  libraries = ["places"],
  loaderOptions = {},
  onLoadError = console.error,
}: UseLoadGoogleApiProps) {
  const [apiLoaded, setApiLoaded] = useState(false);

  useEffect(() => {
    async function load() {
      try {
        if (!apiKey) {
          throw new Error(
            "Google Maps API key needs to be passed in the props"
          );
        }
        if (
          !window.google ||
          !window.google.maps ||
          !window.google.maps.places
        ) {
          await new Loader({
            apiKey,
            libraries,
            ...loaderOptions,
          }).load();
          setApiLoaded(true);
        } else {
          setApiLoaded(true);
        }
      } catch (error) {
        setApiLoaded(false);
        onLoadError(error);
      }
    }

    load();
  }, [apiKey, libraries, loaderOptions, onLoadError]);

  return {
    apiLoaded,
  };
}
