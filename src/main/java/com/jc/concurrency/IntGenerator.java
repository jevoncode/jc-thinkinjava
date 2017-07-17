package com.jc.concurrency;

/**
 * 共享资源生成器接口
 * @author jevoncode
 *
 */
public abstract class IntGenerator {
	private volatile boolean canceled = false;

	public abstract int next();

	// Allow this to be canceled:
	public void cancel() {
		canceled = true;
	}

	public boolean isCanceled() {
		return canceled;
	}
}