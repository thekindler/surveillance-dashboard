import { Component, OnInit } from '@angular/core';

import { LoginService } from 'app/core/login/login.service';
import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/user/account.model';
import { NGXLogger } from 'ngx-logger';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss'],
})
export class HomeComponent implements OnInit {
  account: Account | null = null;

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
    this.logger.log('check', this.account.authorities.includes('ROLE_ADMIN'));

    return this.account.authorities.includes('ROLE_CONFIGURATOR');
  }
}
