package org.codingdojo.kata.potter;

import static java.lang.System.getProperty;
import static org.jbehave.core.reporters.Format.ANSI_CONSOLE;
import static org.jbehave.core.reporters.Format.CONSOLE;

import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.SystemUtils;
import org.codingdojo.kata.potter.steps.PotterSteps;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToPackagedName;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

public class PotterStories extends JUnitStories {

	@Override
	public Configuration configuration() {
		Properties viewResources = new Properties();

		viewResources.put("decorateNonHtml", "true");

		return new MostUsefulConfiguration().useStoryControls(
				new StoryControls().doDryRun(false)
						.doSkipScenariosAfterFailure(false))
				.useStoryReporterBuilder(
						new StoryReporterBuilder()
								.withDefaultFormats()
								.withFormats(
										hasAnsiConsole() ? ANSI_CONSOLE
												: CONSOLE)
								.withPathResolver(new ResolveToPackagedName())
								.withViewResources(viewResources)
								.withFailureTrace(true)
								.withFailureTraceCompression(true));
	}

	private boolean hasAnsiConsole() {
		return getProperty("maven.home") != null && !SystemUtils.IS_OS_WINDOWS;
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(), new PotterSteps());
	}

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths("src/test/resources", "**/*.story",
				"");
	}
}
