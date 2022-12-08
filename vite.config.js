import {spawnSync} from "child_process";
import {resolve} from 'path'
import {createHtmlPlugin} from 'vite-plugin-html'

// https://vitejs.dev/config/
export default ({mode}) => {
    function isDev() {
        return mode !== "production";
    }

    function printSbtTask(task) {
        const sbtLocation = resolve(process.cwd())
        const args = ["--error", "--batch", `print ${task}`];
        const options = {
            cwd: sbtLocation,
            stdio: [
                "pipe", // StdIn.
                "pipe", // StdOut.
                "inherit", // StdErr.
            ],
        };
        const result = process.platform === 'win32'
            ? spawnSync("sbt.bat", args.map(x => `"${x}"`), {shell: true, ...options})
            : spawnSync("sbt", args, options);

        if (result.error)
            throw result.error;
        if (result.status !== 0)
            throw new Error(`sbt process failed with exit code ${result.status}`);

        return result.stdout.toString('utf8').trim();
    }

    const getPublicPath = isDev()
        ? printSbtTask("publicFolderDev")
        : printSbtTask("publicFolderProd");

    return {
        publicDir: './src/main/resources/public',
        plugins: [createHtmlPlugin({minify: !isDev()})],
        resolve: {
            alias: [
                {
                    find: "@linkOutputDir",
                    replacement: getPublicPath,
                },
            ],
        },
    }
}