import { Plugins } from '@capacitor/core';
const { Storage } = Plugins;

export const  setObject = async (key, value) => {
   return await Storage.set({
     key: key,
     value: value
   });
 }

 
export const  getObject = async (key) => {
  return await Storage.get({ key: key });
}

export const  deleteObject = async (key) => {
  return await Storage.remove({ key: key });
}
