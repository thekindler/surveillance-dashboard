import { Component, OnInit } from '@angular/core';

import { LoginService } from 'app/core/login/login.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { NGXLogger } from 'ngx-logger';

export interface Tile {
  color: string;
  cols: number;
  rows: number;
  text: string;
}

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss'],
})
export class HomeComponent implements OnInit {
  account: Account | null = null;

  tiles: Tile[] = [
    { text: 'One', cols: 3, rows: 1, color: 'lightblue' },
    { text: 'Two', cols: 1, rows: 2, color: 'lightgreen' },
    { text: 'Three', cols: 1, rows: 1, color: 'lightpink' },
    { text: 'Four', cols: 2, rows: 1, color: '#DDBDF1' },
  ];

  constructor(private logger: NGXLogger, private accountService: AccountService, private loginService: LoginService) {}

  ngOnInit(): void {
    this.accountService.identity().subscribe(account => (this.account = account));
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.loginService.login();
  }

  isConfigurator(): boolean {
    if (this.account == null) return false;

    this.logger.log('message', this.account.authorities);
    // this.logger.log('check', this.account.authorities.includes('ROLE_CONFIGURATOR'));

    return this.account.authorities.includes('ROLE_CONFIGURATOR');
  }

  isAdmin(): boolean {
    if (this.account == null) return false;

    this.logger.log('message', this.account.authorities);
    this.logger.log('check', this.account.authorities);

    return this.account.authorities.includes('ROLE_USER');
  }
}
