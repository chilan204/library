import {ApplicationConfig, importProvidersFrom, provideZoneChangeDetection} from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideNzIcons } from 'ng-zorro-antd/icon';
import { routes } from './app.routes';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {icons} from '../icons-provider';
import { provideToastr } from 'ngx-toastr';
import {authInterceptor} from './core/interceptors/auth.interceptor';
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideNzIcons(icons),
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([authInterceptor])
    ),
    provideAnimationsAsync(),
    provideToastr(),
    importProvidersFrom(FontAwesomeModule)
  ]
};
