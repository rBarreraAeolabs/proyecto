import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {IPlantilla} from 'app/shared/model/plantilla.model';

@Component({
    selector: 'jhi-plantilla-detail',
    templateUrl: './plantilla-detail.component.html'
})
export class PlantillaDetailComponent implements OnInit {
    plantilla: IPlantilla;

    constructor(private activatedRoute: ActivatedRoute) {
    }

    ngOnInit() {
        this.activatedRoute.data.subscribe(({plantilla}) => {
            this.plantilla = plantilla;
        });
    }

    previousState() {
        window.history.back();
    }
}
