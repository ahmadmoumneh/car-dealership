import { Component, Input, OnInit } from '@angular/core';
import { Form } from 'src/app/classes/form';
import { Contact } from 'src/app/classes/contact';
import { ContactService } from 'src/app/services/contact.service';
import { FormService } from 'src/app/services/form.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  currentContact: Contact;

  @Input() form: Form;

  constructor (
    private formService: FormService,
    private contactService: ContactService
  ) {
  }

  ngAfterViewInit() {
    this.formService.addAllControls(this.form);
  }

  ngOnInit() {
    this.currentContact = new Contact();
    this.form = this.formService.buildForm(this.save, this.reset);
  } 

  save = () => {
    this.contactService.addContact(this.currentContact).subscribe(contact => {
      console.log(contact);
    });
  };

  reset = () => this.currentContact.contactId = null;
}
