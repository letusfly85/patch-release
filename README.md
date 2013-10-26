h1. patch release tool


h2. introduction

it enables to generate patch release sets


h2. usage

usage is below

h3. copy repositories

<pre><code>
gradle copyRepository -Prunargs=${dummy}
</code></pre>


h3. download patch sources

<pre><code>
gradle run -Prunargs=com.jellyfish85.patchRelease.executor.DownloadModules2Workspace,${ticketNumber}
</code></pre>


h3. copy java to build workspace

<pre><code>
gradle copyJava  -Prunargs=${ticketNumber}
</code></pre>