import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { MatTableDataSource, MatTableModule } from "@angular/material/table";

interface TableColumn {
  caption: string;
  field: string;
}

@Component({
  standalone: true,
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css'],
  imports: [CommonModule, MatTableModule]
})
export class TableComponent implements OnInit, OnChanges {

  @Input() 
  columns: Array<TableColumn>;
  
  @Input() 
  dataSource: Array<any>;

  @Output()
  dataSourceChange: EventEmitter<Array<any>> = new EventEmitter<Array<any>>();
  
  public displayedColumns:  Array<string> = [];
  public _dataSource : MatTableDataSource<any> = new MatTableDataSource();

  ngOnInit(): void {    
    this.displayedColumns = this.columns.map((tableColumn: TableColumn) => tableColumn.caption);
    this._dataSource = new MatTableDataSource(this.dataSource);
  }

  ngOnChanges() {
    this._dataSource = new MatTableDataSource(this.dataSource);
  }
}