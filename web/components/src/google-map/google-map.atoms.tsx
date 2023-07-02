import { atom } from "jotai";

export const googleMapAtom = atom<google.maps.Map | undefined>(undefined);
