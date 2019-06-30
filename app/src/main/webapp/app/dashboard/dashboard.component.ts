import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'jhi-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
    public barCharOptions: any = {
        scaleShowVerticalLines: false,
        response: true
    };

    public barCharLabels: string[] = ['2016', '2017', '2018'];

    public barChartType = 'bar';

    public barChartLegend = true;

    public barChartData: any[] = [{ data: [65, 59, 80], label: 'Series A' }, { data: [28, 48, 19], label: 'Series B' }];

    // eventos
    public chartClicked(e: any): void {}

    public chartHovered(e: any): void {}

    public randomize(): void {
        // only changes 3 values
        const data = [Math.round(Math.random() * 100), Math.round(Math.random() * 100), Math.round(Math.random() * 100)];

        const clone = JSON.parse(JSON.stringify(this.barChartData));
        clone[0].data = data;

        this.barChartData = clone;
    }

    constructor() {}

    ngOnInit(): void {}
}
