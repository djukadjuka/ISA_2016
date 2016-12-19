import { ISAProjectPage } from './app.po';

describe('isa-project App', function() {
  let page: ISAProjectPage;

  beforeEach(() => {
    page = new ISAProjectPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
