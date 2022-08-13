import { Component } from '@angular/core';
import { StorageService } from './_services/storage.service';
import { AuthService } from './_services/auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private roles: string[] = [];
  isLoggedIn = false;
  showEmployeeBoard = false;
  showManagerBoard = false;
  email?: string;

  constructor(private storageService: StorageService, private authService: AuthService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.storageService.isLoggedIn();
  console.log(1+":"+this.isLoggedIn);
    if (this.isLoggedIn) {
      const user = this.storageService.getUser();
      this.roles = user.roles;

      this.showEmployeeBoard = this.roles.includes('ROLE_EMPLOYEE');
      this.showManagerBoard = this.roles.includes('ROLE_MANAGER');

      this.email = user.email;
    }
  }

  logout(): void {
    console.log(2);
    this.authService.logout().subscribe({
      next: res => {
      
        this.storageService.clean();
        },
      error: err => {
        console.log(err);
      }
    });
    
    window.location.reload();
  }
}
