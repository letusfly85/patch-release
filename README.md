h2. usage

h3. download patch sources

<pre><code>
gradle run -Prunargs=com.jellyfish85.patchRelease.executor.DownloadModules2Workspace,${ticketNumber}
</code></pre>

h3. copy repositories

<pre><code>
gradle copyRepository -Prunargs=0
</code></pre>