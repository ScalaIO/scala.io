/** @type {import('ts-jest').JestConfigWithTsJest} **/
export default {
  testEnvironment: "node",
  transform: {
    "^.+.ts?$": ["ts-jest", {}],
  },
  preset: "jest-puppeteer",
};
