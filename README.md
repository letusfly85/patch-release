# patch release tool


## introduction

it enables to generate patch release sets


## usage

usage is below

### copy repositories

<pre><code>
gradle copyRepository -Prunargs=${dummy}
</code></pre>


### download patch sources

<pre><code>
gradle run -Prunargs=com.jellyfish85.patchRelease.executor.DownloadModules2Workspace,${ticketNumber}
</code></pre>


### copy java to build workspace

<pre><code>
gradle copyJava  -Prunargs=${ticketNumber}
</code></pre>