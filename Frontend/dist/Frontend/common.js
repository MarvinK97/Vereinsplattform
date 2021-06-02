(self["webpackChunkfrontend"] = self["webpackChunkfrontend"] || []).push([["common"],{

/***/ 7574:
/*!*******************************************!*\
  !*** ./src/app/core/guards/auth.guard.ts ***!
  \*******************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "AuthGuard": () => (/* binding */ AuthGuard)
/* harmony export */ });
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ 7716);
/* harmony import */ var _services_auth_token_storage_service__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! ../services/auth/token-storage.service */ 4699);
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ 9895);



class AuthGuard {
    constructor(tokenStorageService, router) {
        this.tokenStorageService = tokenStorageService;
        this.router = router;
    }
    canActivate(route, router) {
        const isAuth = !!this.tokenStorageService.getToken();
        if (isAuth) {
            return true;
        }
        return this.router.createUrlTree(['/auth/login']);
    }
}
AuthGuard.ɵfac = function AuthGuard_Factory(t) { return new (t || AuthGuard)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵinject"](_services_auth_token_storage_service__WEBPACK_IMPORTED_MODULE_0__.TokenStorageService), _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵinject"](_angular_router__WEBPACK_IMPORTED_MODULE_2__.Router)); };
AuthGuard.ɵprov = /*@__PURE__*/ _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjectable"]({ token: AuthGuard, factory: AuthGuard.ɵfac, providedIn: 'root' });


/***/ }),

/***/ 1894:
/*!***********************************************!*\
  !*** ./src/app/core/services/club.service.ts ***!
  \***********************************************/
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   "ClubService": () => (/* binding */ ClubService)
/* harmony export */ });
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! @angular/common/http */ 1841);
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ 7716);



const API_URL = 'http://localhost:8080/api/clubs/';
const httpOptions = {
    headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_0__.HttpHeaders({ 'Content-Type': 'application/json' })
};
class ClubService {
    constructor(http) {
        this.http = http;
    }
    getClub() {
        return this.http.get(API_URL + 'users');
    }
    joinClub(id) {
        return this.http.put(API_URL + 'users/' + id, httpOptions);
    }
    leaveClub(id) {
        return this.http.delete(API_URL + 'users/' + id, httpOptions);
    }
    getActiveRequests(clubid) {
        return this.http.get(API_URL + clubid + "/activerequests");
    }
    acceptRequest(id) {
        return this.http.put(API_URL + "requests/" + id, httpOptions);
    }
}
ClubService.ɵfac = function ClubService_Factory(t) { return new (t || ClubService)(_angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵinject"](_angular_common_http__WEBPACK_IMPORTED_MODULE_0__.HttpClient)); };
ClubService.ɵprov = /*@__PURE__*/ _angular_core__WEBPACK_IMPORTED_MODULE_1__["ɵɵdefineInjectable"]({ token: ClubService, factory: ClubService.ɵfac, providedIn: 'root' });


/***/ })

}]);
//# sourceMappingURL=common.js.map