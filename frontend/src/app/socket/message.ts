import { DatePipe } from '@angular/common';

export interface Message {
    message: string;
    fromId: string;
    toId: string;
    date: string;
}
