import puppeteer from "puppeteer";

describe("Router", () => {
  const nantes2024TalkSlug = "armored-type-safety-with-iron";
  const nantes2024TalkTitle = "Armored type safety with Iron";

  it("Should display talks accessed from direct URL", async () => {
    // Launch the browser and open a new blank page
    const browser = await puppeteer.launch();
    const page = await browser.newPage();

    // Check that legacy URLs are redirected to the new ones
    await page.goto(`http://localhost:5173/talks/${nantes2024TalkSlug}`);
    await page.waitForSelector(`h1 ::-p-text(${nantes2024TalkTitle})`);

    await browser.close();
  });

  it("Should display talks accessed from legacy talk route", async () => {
    // Launch the browser and open a new blank page
    const browser = await puppeteer.launch();
    const page = await browser.newPage();

    await page.goto(
      `http://localhost:5173/talks/nantes-2024/${nantes2024TalkSlug}/`
    );
    await page.waitForSelector(`h1 ::-p-text(${nantes2024TalkTitle})`);

    await browser.close();
  });
});
