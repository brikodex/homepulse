import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'currency',
  standalone: true,
})
export class CurrencyPipe implements PipeTransform {
  transform(value: unknown, ...args: unknown[]): unknown {
    //Append a dollar sign
    return `${value} Ar`;
  }
}
