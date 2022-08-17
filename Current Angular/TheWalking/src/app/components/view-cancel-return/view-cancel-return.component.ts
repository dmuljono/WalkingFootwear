import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CancelOrder } from 'src/app/model/cancel-order';
import { ReturnOrder } from 'src/app/model/return-order';
import { ManagerService } from 'src/app/services/manager.service';

@Component({
  selector: 'app-view-cancel-return',
  templateUrl: './view-cancel-return.component.html',
  styleUrls: ['./view-cancel-return.component.css']
})
export class ViewCancelReturnComponent implements OnInit {

  isSuccessful = false;
  isSubmittedFailed = false;
  errorMessage = '';
  returns: ReturnOrder[];
  cancels: CancelOrder[];

  constructor(private pserv: ManagerService, private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.listOrder();
    }
    );
  }


  listOrder(){
    this.pserv.getAllCancels().subscribe(
      data=>{
        this.cancels=data;
        console.log(data);
      }
    );
    this.pserv.getAllReturns().subscribe(
      data=>{
        this.returns=data;
        console.log(data);
      }
    );
  }
}

