import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ProductGroupComponent } from './product-group.component';
import { ProductGroupDetailComponent } from './product-group-detail.component';
import { ProductGroupPopupComponent } from './product-group-dialog.component';
import { ProductGroupDeletePopupComponent } from './product-group-delete-dialog.component';

export const productGroupRoute: Routes = [
    {
        path: 'product-group',
        component: ProductGroupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductGroups'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'product-group/:id',
        component: ProductGroupDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductGroups'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const productGroupPopupRoute: Routes = [
    {
        path: 'product-group-new',
        component: ProductGroupPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductGroups'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'product-group/:id/edit',
        component: ProductGroupPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductGroups'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'product-group/:id/delete',
        component: ProductGroupDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'ProductGroups'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
