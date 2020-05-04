// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  api: {
    LOGIN: "http://localhost:8080/mega-hack/oauth/token",
    LOGIN_NETWORK: "http://localhost:8080/mega-hack/oauth2",
    USERS: "http://localhost:8080/mega-hack/users",
    CHAT: "http://localhost:8080/mega-hack/chat",
    ESTABLISMENTS: "http://localhost:8080/mega-hack/establishment",
    PRODUCT: "http://localhost:8080/mega-hack/product",
    ORDER: "http://localhost:8080/mega-hack/orders",
    DELIVERY:"http://localhost:8080/mega-hack/delivery"
  },
  ws: "ws://localhost:8080"
};

/*
* For easier debugging in development mode, you can import the following file
* to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
*
* This import should be commented out in production mode because it will have a negative impact
* on performance if an error is thrown.
*/
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
