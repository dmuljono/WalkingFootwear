import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { StorageService } from '../_services/storage.service';

@Component({
  selector: 'app-board-manager',
  templateUrl: './board-manager.component.html',
  styleUrls: ['./board-manager.component.css']
})
export class BoardManagerComponent implements OnInit {
  content?: string;
  showManagerBoard:boolean = false;
  private roles: string[] = [];
  

  constructor(private userService: UserService, private storageService: StorageService) { }

  ngOnInit(): void {
    const user = this.storageService.getUser();
    this.roles = user.roles;
    this.showManagerBoard = this.roles.includes('MANAGER');
    this.userService.getManagerBoard().subscribe({
      next: data => {
        this.content = data;
      },
      error: err => {
        if (err.error) {
          try {
            const res = JSON.parse(err.error);
            this.content = res.message;
          } catch {
            this.content = `Error with status: ${err.status} - ${err.statusText}`;
          }
        } else {
          this.content = `Error with status: ${err.status}`;
        }
      }
    });
  }
}
