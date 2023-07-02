// import { AddressTypes } from './google-autocomplete-with-map';

export function noop() {
  /** */
}

export const PLACE_DETAILS_FIELDS = [
  "name",
  "address_component",
  "adr_address",
  "formatted_address",
  "geometry",
];

// export const addressLookups: AddressTypes[] = [
// 	'car_dealer',
// 	'point_of_interest',
// 	'store',
// 	'establishment',
// 	'locality',
// 	'postal_code',
// ];

export function getMatchingAddressCriteria(
  geocodedResults: google.maps.GeocoderResult[] = [],
  types: string[] = []
) {
  let matchingAddress: google.maps.GeocoderResult | undefined;

  if (types.length === 0 && geocodedResults.length === 0) {
    return undefined;
  }

  for (let index = 0; index < types.length; index++) {
    const foundAddress = geocodedResults.find((r) =>
      r.types.includes(types[index])
    );
    if (foundAddress) {
      matchingAddress = foundAddress;
      break;
    }
  }
  return matchingAddress;
}

export const convertLatLong = (arr: any) => {
  if (arr === undefined) return;
  return +arr.data?.[0]?.value;
};
